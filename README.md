In order to run this application, you need to have Docker installed. The docker image of this application is available on public AWS ECR.

Please follow below steps to launch the application.

**Pre-requisite:** MySQL DB need to be setup and configured (either on your local machine or on cloud). The DB URL, username and password of the DB have to be provided while starting the container.

**Step 1:** Use below command to authenticate with AWS ECR.

aws ecr-public get-login-password --region us-east-1 | docker login --username AWS --password-stdin public.ecr.aws/j2p2l7e6

**Step 2:** Create a .env File for DB configuration.

DB_URL=provide-db-url-here

DB_USERNAME=provide-db-username-here

DB_PASSWORD=provide-db-password-here

**Step 3:** Use the .env File When Running the Container. To run the container, use below command.

docker run -p80:8080 -d --env-file .env --name devserver public.ecr.aws/j2p2l7e6/hello-aws:myrestapp-v1

