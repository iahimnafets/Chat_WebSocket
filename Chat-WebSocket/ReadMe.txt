
*********  26 June 2022 -  Mihai Stefan ************

For this project I have also created a video to see some steps


1) Unzip the .ZIP file

2) Import into IntelliJ as an existing project

3) Install Postgres in your PC, Open a new terminal and write all this commands ( I use Mac ):

   a) brew update
   b) brew install postgresql
   c) brew services start postgresql
   d) CREATE ROLE mihai WITH LOGIN PASSWORD 'password';
      ALTER ROLE chris CREATEDB;
      Or create another user/pass like you want and modify: application.properties

   c) If you have problems, Followed this guide for install Postgres  ( I followed this )
       https://daily-dev-tips.com/posts/installing-postgresql-on-a-mac-with-homebrew/

    Now you are ready to move for next steps


3) Open the "terminal" tab in IntelliJ and run the command:
     mvn clean install

5) Open the class: ChatApplication and launch the application

6) Open a browser, now you can test via swagger or via browser for chat

    a) Open browser for chat, for first user: http://localhost:8080/
       open another tab for new user: http://localhost:8080/

    b) If you want to se the version of application or all the messages in database, open:
      http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/


