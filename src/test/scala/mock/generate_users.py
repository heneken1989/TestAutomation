from faker import Faker

fake = Faker()

# Generate names and emails with numbers from 1 to 510
data = [(f"{i}@gmail.com", fake.email()) for i in range(1, 511)]

# Print the result
for i, (name, email) in enumerate(data, start=1):
    print(f"{email},{name}")