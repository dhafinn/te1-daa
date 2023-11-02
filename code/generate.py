import random

def generate_dataset():
    file_name = "dataset3.txt"
    n = 2 ** 16  # The number of elements (2^9)
    min_value = 1  # Minimum value for random numbers
    max_value = 1000  # Maximum value for random numbers
    with open(file_name, "w") as file:
        # 2. Write the text to the file
        dataset = [random.randint(min_value, max_value) for _ in range(n)]
         # write each number to file
        for number in dataset:
            file.write(str(number) + "\n")

if __name__ == "__main__":
    generate_dataset()
    