# nltk LancasterStemmer chatbot
# Student ID:24055226S                  Name: HO Sze Tung

import nltk
from nltk.stem.lancaster import LancasterStemmer
stemmer = LancasterStemmer()
nltk.download('punkt') # Needed for Google Colab
nltk.download('punkt_tab') # Needed for Google Colab 2025.02.06

import numpy
import random
import json

from tensorflow.keras import layers, models

def read_intent():
   with open('intents.json') as file:
       data = json.load(file)
   words = []
   labels = []
   docs_x = []
   docs_y = []
   
   for intent in data["intents"]:
       for pattern in intent["patterns"]:
           wrds = nltk.word_tokenize(pattern)
           words.extend(wrds)
           docs_x.append(wrds)
           docs_y.append(intent["tag"])

       if intent["tag"] not in labels:
           labels.append(intent["tag"])

   words = [stemmer.stem(w.lower()) for w in words if w != "?"]
   words = sorted(list(set(words)))
   labels = sorted(labels)
   return labels, words, docs_x, docs_y, data

def make_BOW(labels, words, docs_x, docs_y):
   training = []
   output = []
   out_empty = [0 for _ in range(len(labels))]

   for x, doc in enumerate(docs_x):
       bag = []
       wrds = [stemmer.stem(w.lower()) for w in doc]

       for w in words:
           if w in wrds:
              bag.append(1)
           else:
              bag.append(0)

       output_row = out_empty[:]
       output_row[labels.index(docs_y[x])] = 1
       training.append(bag)
       output.append(output_row)

   return numpy.array(training), numpy.array(output)

def bag_of_words(s, words):
    bag = [0 for _ in range(len(words))]

    s_words = nltk.word_tokenize(s)
    s_words = [stemmer.stem(word.lower()) for word in s_words]

    for se in s_words:
        for i, w in enumerate(words):
            if w == se:
                bag[i] = 1

    return numpy.array(bag)

def chat():
    print("Start talking with the bot (type quit to stop)!")
    tag = ""
    while tag != "goodbye":
        inp = input("You: ")

        ip = bag_of_words(inp, words)
        ip = numpy.array(ip).reshape(1, -1)  # Reshape to (1, number_of_features)

        results = model.predict(ip)
        results_index = numpy.argmax(results)
        tag = labels[results_index]

        for tg in data["intents"]:
            if tg['tag'] == tag:
                responses = tg['responses']
        print(random.choice(responses))

###########################################
## This is the main logic of the program ##
###########################################
labels, words, docs_x, docs_y, data = read_intent()
training, output = make_BOW(labels, words, docs_x, docs_y)

# Define the model
model = models.Sequential()
model.add(layers.Input(shape=(len(training[0]),)))             # Input layer
model.add(layers.Dense(8, activation='relu'))                  # First hidden layer
model.add(layers.Dense(8, activation='relu'))                  # Second hidden layer
model.add(layers.Dense(len(output[0]), activation='softmax'))  # Output layer

# Compile the model
model.compile(optimizer='adam',
              loss='categorical_crossentropy',
              metrics=['accuracy'])

model.summary()

# The model is now ready to be trained using model.fit()
model.fit(training, output, epochs=500, batch_size=8, verbose=1)

chat()