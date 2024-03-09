# Using a lightweight Python image as the base
FROM python:3.11-alpine

# Set the working directory
WORKDIR /app

# Copy the application code
COPY . /app

# Install dependencies
RUN pip install -r requirements.txt

# Expose port 5000 (Flask's default port)
EXPOSE 5000

# Run the application
CMD ["python", "app.py"]