# SEHS4678 AI Exercise v26a
# maze_solver.py  
# Student Name (ID): HO Sze Tung (24055226S)
import heapq

# ==== The Node class ====
class Node():
    # >>>> Add code for the class Node (Hint: 4 lines expected)
    def __init__(self, state, parent, action):
        self.state = state
        self.parent = parent
        self.action = action

# ==== The StackFrontier class ====
class StackFrontier():
    #--- The constructor
    def __init__(self):
        self.frontier = []

    def add(self, node):
        self.frontier.append(node)

    def contains_state(self, state):
        return any(node.state == state for node in self.frontier)

    def empty(self):
        return len(self.frontier) == 0

    def remove(self):
        if self.empty():
            raise Exception("empty frontier")
        else:
            # >>>> Add code for removing the last item in the frontier and return the item removed.
            node = self.frontier[-1]
            self.frontier = self.frontier[:-1]
            return node

# ==== The Maze class ====
class Maze():

    #--- The constructor
    def __init__(self, filename):

        # Read file and set height and width of maze
        with open(filename) as f:
            contents = f.read()

        # Validate start and goal
        # >>>> Add an if statement check that the maze has exactly one start point.
		# >>>> If that is not the case, raise an exception to state that point. 
        if contents.count("A") != 1:
            raise Exception("maze must have exactly one start point")
        # >>>> Add an if statement check that the maze has exactly one goal.
		# >>>> If that is not the case, raise an exception to state that point. 

        if contents.count("B") != 1:
            raise Exception("maze must have exactly one goal")

        # Determine height and width of maze
        contents = contents.splitlines()
        self.height = len(contents)
        self.width = max(len(line) for line in contents)

        # Keep track of walls
        self.walls = []
        for i in range(self.height):
            row = []
            for j in range(self.width):
                try:
                    if contents[i][j] == "A":
                        self.start = (i, j)
                        row.append(False)
                    elif contents[i][j] == "B":
                        self.goal = (i, j)
                        row.append(False)
                    elif contents[i][j] == " ":
                        row.append(False)
                    else:
                        row.append(True)
                except IndexError:
                    row.append(False)
            self.walls.append(row)

        self.solution = None


    # Show the maze to the user
    def print(self, show_explored = False):
        solution = self.solution[1] if self.solution is not None else None
        # Create the explored set variable for the A* algorithm with the `.` symbol to demonstrate the search space.
        explored = getattr(self, "explored_visual", None) if show_explored else None
        print()
        for i, row in enumerate(self.walls):
            for j, col in enumerate(row):
                if col:
                    print("█", end="")
                elif (i, j) == self.start:
                    print("A", end="")
                elif (i, j) == self.goal:
                    print("B", end="")
                elif solution is not None and (i, j) in solution:
                    print("*", end="")
                elif explored is not None and (i, j) in explored:
                    print(".", end="")    
                else:
                    print(" ", end="")
            print()
        print()


    # Find out the surrounding cells that are not walls
    # Return the findings in a list
    def neighbors(self, state):
        row, col = state
        candidates = [
            ("up", (row - 1, col)),
            ("down", (row + 1, col)),
            ("left", (row, col - 1)),
            ("right", (row, col + 1))
        ]

        result = []
        for action, (r, c) in candidates:
            if 0 <= r < self.height and 0 <= c < self.width and not self.walls[r][c]:
                result.append((action, (r, c)))
        return result

    # Finds a solution to maze, if one exists
    # Depth-First Search
    def solveDFS(self):

        # Keep track of number of states explored
        self.num_explored = 0

        # Initialize frontier to just the starting position
        start = Node(state=self.start, parent=None, action=None)
        frontier = StackFrontier()
        frontier.add(start)

        # Initialize an empty explored set
        self.explored = set()

        # Keep looping until solution found
        while True:

            # If nothing left in frontier, then no path
            if frontier.empty():
                raise Exception("no solution")

            # Choose a node from the frontier
            node = frontier.remove()
            self.num_explored += 1

            # If node is the goal, then we have a solution
            if node.state == self.goal:
                actions = []
                cells = []
                while node.parent is not None:
                    actions.append(node.action)
                    cells.append(node.state)
                    node = node.parent
                actions.reverse()
                cells.reverse()
                self.solution = (actions, cells)
                print("Steps: ", len(cells))
                return

            # Mark node as explored
            self.explored.add(node.state)

            # Add neighbors to frontier
            for action, state in self.neighbors(node.state):
                if not frontier.contains_state(state) and state not in self.explored:
                    child = Node(state=state, parent=node, action=action)
                    frontier.add(child)
                    
    # A* Search Algorithm
    def solveAStar(self):
        def heuristic(state):
            # Manhattan distance to goal
            (x1, y1) = state
            (x2, y2) = self.goal
            return abs(x1 - x2) + abs(y1 - y2)

        frontier = []
        counter = 0     # tie-breaker counter
        start = Node(state=self.start, parent=None, action=None)
        heapq.heappush(frontier, (heuristic(self.start), counter, 0, start))

        self.explored = set()
        self.num_explored = 0
        explored_visual = set()

        while frontier:
            _, _, g_cost,node = heapq.heappop(frontier)
            self.num_explored += 1
            
            # Goal check
            if node.state == self.goal:
                actions, cells = [], []
                while node.parent is not None:
                    actions.append(node.action)
                    cells.append(node.state)
                    node = node.parent
                actions.reverse()
                cells.reverse()
                self.solution = (actions, cells)
                self.explored_visual = explored_visual
                print("Steps: ", len(cells))
                print("Explored nodes: ", self.num_explored)
                return
                
            self.explored.add(node.state)
            explored_visual.add(node.state)
            
            # Expand neighbors
            for action, state in self.neighbors(node.state):
                if state not in self.explored:
                    child = Node(state=state, parent=node, action=action)
                    counter += 1
                    new_g = g_cost + 1
                    f_cost = new_g + heuristic(state)
                    heapq.heappush(frontier, (f_cost, counter, new_g, child))


# ****************************
# The main program starts here
# ****************************
print("Maze v1.1: Started")
m = Maze("maze.txt")

print("Maze:")
m.print()

print("Solving with DFS ...")
m.solveDFS()
print("States Explored:", m.num_explored)
print("DFS Solution:")
m.print()

print("Solving with A* ...")
m.solveAStar()
print("A* Solution:")
m.print(show_explored=True)

print("Maze v1.1: Completed")
