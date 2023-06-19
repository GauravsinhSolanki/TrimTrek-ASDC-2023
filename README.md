## Project Overview
The Barber Booking and Scheduling App is a comprehensive platform that enables users to schedule and manage appointments with barbers conveniently. The app aims to streamline the process of booking barber services, providing users with a seamless and efficient experience. 

**Deployed application: https://group07-trimtrek.netlify.app/** 
**GitLab Link: https://git.cs.dal.ca/courses/2023-summer/csci-5308/group07/** 
**yml file link: https://git.cs.dal.ca/courses/2023-summer/csci-5308/group07/-/blob/f7dfa63dbceb12222e8b7b17d4f6ac42ba40d61c/.gitlab-ci.yml**

### Authors

#### - Gauravsinh Bharatsinh Solanki (B00932065)
#### - Neel Patel (B00923803)
#### - Rakshit Ranpariya (B00946421)
#### - Jay Rana (B00932024)
#### - Sakib Sadman (B00934956)




### Roadmap

#### (1) Project Initiation:

- Set up Jira board with relevant tasks,stories and epics.
- Define project scope, goals, and requirements.
- Define the sprints for the project.
- Each sprint is for 2 weeks.

#### (2) Continuous Integration and Deployment (CI/CD):

- Clone the GitLab repositary and created a custome runner.In this process we identified that whether we needed the individule runner or a common runner for whole group project is enough to create CI/CD pipeline.

- After that we implemented maven 3.8.1 and openJDK 11.

- We created .yml file and in that file there are two stages. (1) build, and (2) test

- Then we modifed the .yml for backend and frontend separatly.

#### (3) Frontend Development:

- We creted individual repo for our project and define the file struture for the frontend part.

- For frontend we are going with the React.js.

- We also push one demo login page to check our pipeline and in that we succeed the task.

#### (4) Backend Development:

- We creted individual repo for our project and define the file struture for the backend part.

- For backend we are going with the Springboot.

- We also pushed login backend part to check and test our CICD pipeline.






## Lessons Learned
During this process we leaned some lessons.

##### (1) During the frontend CICD pipeline testing we failed two times our mistake was there are one depenedcy missing for babel we installed that. And anthoer warning was for the href that we replaced with the button as of now.


##### (2) During the backend part of CICD pipeline testing first we have faced issue for java versions first we choose openJDK 11 while it needed openJDK 16 once we changed that in .yml file and trigger the CICD and we worked perfectly.
## Deployment

- We have deployed our application on the Netlify server. 

- Here you can see the application: https://group07-trimtrek.netlify.app/

