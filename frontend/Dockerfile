# Use a more recent LTS version of Node.js
FROM node:lts-alpine

# Create app directory
WORKDIR /frontend

# Install app dependencies
# A wildcard is used to ensure both package.json and package-lock.json are copied
COPY package*.json ./

# Install all dependencies, including devDependencies needed for the start script
RUN npm install

# Copy app source code
COPY . .

# Expose the port your app runs on
EXPOSE 3000

# Run the command to start your frontend application
CMD ["npm", "start"]