'''
Student Name: HO Sze Tung; Student ID: 24055226S
Reference: Liu, Y. H. (2020). Python machine learning by example. Packt Publishing Ltd.
'''

# Import TensorFlow library
import tensorflow as tf

# Load the Fashion MNIST dataset
fashion_mnist = tf.keras.datasets.fashion_mnist
(train_images, train_labels), (test_images, test_labels) = fashion_mnist.load_data()

# Print the training labels (numeric class IDs)
#>>> Add your statements here
print(train_labels)

# Define class names corresponding to label indices (0–9)
class_names = ['T-shirt/top', 'Trouser', 'Pullover', 'Dress', 'Coat',
               'Sandal', 'Shirt', 'Sneaker', 'Bag', 'Ankle boot']

# Print the shape of training and testing datasets
#>>> Add your statements here
print(train_images.shape)
print(test_images.shape)

# Import matplotlib for visualization
import matplotlib.pyplot as plt

# Display one sample image from training data (index 42)
i = 3
plt.figure()
plt.imshow(train_images[i])        # Show the image
plt.colorbar()                      # Show pixel intensity scale
plt.grid(False)                     # Remove grid lines
plt.title(class_names[train_labels[i]])  # Show class name as title
plt.show()

# Normalize pixel values from range [0, 255] to [0, 1]
train_images = train_images / 255.0
test_images = test_images / 255.0

# Display the first 16 training images in a 4x4 grid
plt.figure(figsize=(10, 10))
for i in range(16):
    plt.subplot(4, 4, i + 1)        # Create subplot
    plt.subplots_adjust(hspace=.3)  # Adjust spacing
    plt.xticks([])                  # Remove x-axis ticks
    plt.yticks([])                  # Remove y-axis ticks
    plt.grid(False)                 # Remove grid
    plt.imshow(train_images[i], cmap=plt.cm.binary)  # Show image in grayscale
    plt.title(class_names[train_labels[i]])          # Show label
plt.show()

# Reshape images to include channel dimension (required for CNN)
# New shape: (number_of_samples, 28, 28, 1)
X_train = train_images.reshape((train_images.shape[0], 28, 28, 1))
X_test = test_images.reshape((test_images.shape[0], 28, 28, 1))

print(X_train.shape)

# Set random seed for reproducibility
tf.random.set_seed(42)

# Import necessary Keras modules
from tensorflow.keras import layers, models, losses

# Build a Sequential Convolutional Neural Network (CNN) model
m = models.Sequential()

#>>> Add your statements here
m.add(layers.Conv2D(32, (3, 3), activation='relu', input_shape=(28, 28, 1)))
m.add(layers.MaxPooling2D((2, 2)))
m.add(layers.Conv2D(64, (3, 3), activation='relu'))
m.add(layers.MaxPooling2D((2, 2)))
m.add(layers.Conv2D(128, (3, 3), activation='relu'))
m.add(layers.Flatten())
m.add(layers.Dense(64, activation='relu'))
m.add(layers.Dense(10, activation='softmax'))


m.compile(optimizer='adam',
              loss=losses.sparse_categorical_crossentropy,
              metrics=['accuracy'])

# Display model architecture summary
#>>> Add your statements here
m.summary()

# Train the model and validate using test dataset during training
m.fit(X_train, train_labels, validation_data=(X_test, test_labels), epochs=2)

# Evaluate the model on the test dataset
test_loss, test_acc = m.evaluate(X_test, test_labels, verbose=2)

# Print test accuracy
print('Accuracy on test set:', test_acc)

# Generate predictions (probabilities) for test images
predictions = m.predict(X_test)

# Print prediction probabilities for the first test image
#>>> Add your statements here
print(predictions)

# Import NumPy for numerical operations
import numpy as np

# Print predicted label (highest probability index)
print('Predicted label for the first test sample: ', np.argmax(predictions[0]))

# Print true label for comparison
print('True label for the first test sample: ', test_labels[0])