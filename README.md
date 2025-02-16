# Ticket System Application
This repository is used to manage and store the code that is used with the enterpised application. It will be named and the description will be changed once a topic picked.

# Project Design Document

## Introduction

The application that we are going to make is a ticket managing system. This will allow for users to create tickets for current items that need to be worked on. Users will then be able to update the status of those tickets. Users will be able to mark tickets complete as well so that they no longer show on refresh.

Possible Addons:
- Ability to add comments to tickets
- Ability to see historical tickets

## Storyboard  
Screen mockups can be created using tools such as Invision, FluidUI, PowerPoint, Paint, etc.

## Functional Requirements  
Format: (Fill in the square brackets with your own words)

- **As a** [As an Employee]  
- **I want** [I want to be able to create tickets]  
- **So that I can** [So that I can better manage work tasks as they are assigned]

- **As a** [As a Manager]  
- **I want** [I want to be able to view current active tickets]  
- **So that I can** [So that I can see the current status on current projects]

- **As a** [As an Employee]  
- **I want** [I want to be able to change the current status of a ticket]  
- **So that I can** [So that I can track the currents status of a ticket]


1. Creating Tickets (Employee)

    Given an employee is logged into the system

    When the employee clicks the "Create Ticket" button and fills out the required fields (title, description, priority) and submits the form

    Then a new ticket is created with the status "Open" and the employee is notified of successful ticket creation.

    Given an employee is logged into the system

    When the employee clicks the "Create Ticket" button and fills out the required fields (title, description, priority) but leaves the description field blank and submits the form

    Then an error message is displayed indicating that the description field is required, and the ticket is not created.

    Given an employee is logged into the system

    When the employee clicks the "Create Ticket" button and enters a ticket title that exceeds the maximum character limit and submits the form

    Then an error message is displayed indicating the title exceeds the character limit, and the ticket is not created.

    Given an employee is logged into the system

    When the employee clicks the "Create Ticket" button and selects a priority of "High"

    Then the created ticket will have a priority of "High" assigned to it.

2. Viewing Active Tickets (Manager)

    Given a manager is logged into the system

    When the manager navigates to the "Active Tickets" page

    Then a list of all currently open tickets is displayed, showing relevant information such as ticket ID, title, priority, assigned employee (if applicable), and current status.

    Given a manager is logged into the system and there are no active tickets.

    When the manager navigates to the "Active Tickets" page

    Then a message is displayed indicating that there are no active tickets.

    Given a manager is logged into the system and there are several active tickets with varying priorities.

    When the manager navigates to the "Active Tickets" page and sorts the tickets by priority (e.g., highest to lowest)

    Then the tickets are displayed in the selected order of priority.

    Given a manager is logged in and there are a large number of active tickets (e.g., > 50).

    When the manager navigates to the "Active Tickets" page.

    Then the active tickets are displayed in a paginated format, with a manageable number of tickets displayed per page (e.g., 25).

3. Changing Ticket Status (Employee)

    Given an employee is logged in and has a ticket assigned to them

    When the employee views the ticket details and selects a new status from the available options (e.g., "In Progress," "Blocked," "Resolved") and saves the change

    Then the ticket's status is updated to the selected value, and the employee is notified of the successful status change.

    Given an employee is logged in and has a ticket assigned to them with the status "Open"

    When the employee changes the status of the ticket to "Resolved"

    Then the ticket status is updated to "Resolved", and it no longer appears on the active tickets list by default.

    Given an employee is logged in and attempts to change the status of a ticket that is not assigned to them.

    When the employee views the ticket details and attempts to change the status.

    Then an error message is displayed, indicating that the employee does not have permission to change the status of that particular ticket.

    Given an employee is logged in and the current status of the ticket is "Blocked"

    When the employee changes the status to "In Progress"

    Then the ticket status is updated to "In Progress".

**Notes:**
- You should have a few (2-4) requirements.
- The Given/When/Then syntax will form the basis of our unit tests.
- Consider different cases: good data, bad data, multiple sets of data, etc.

## Class Diagram  
- UML-based class diagram.
- **Class Diagram Description:** One or two lines for each class to describe the use of interfaces, classes, and resources.  
  - Don't worry about putting more than a few words per class.
  - This does not need to be thorough.

## JSON Schema  
Your project should have a REST endpoint that emits JSON, which another group can consume. The design document should contain a draft JSON schema for this endpoint.

- You can create a schema from a Java class at [QuickType.io](https://quicktype.io/).
- This Java class is typically a DTO.
- That's all you need at this point.

## Scrum Roles  
Emmanuella - UI Specialist
Aidan - Business Logic and Persistence Specialist
Product Owner/Devops  - Andrew

## GitHub Project Link  
[Insert GitHub project link here]

## Scrum/Kanban Board  
A **scrum or kanban board**, using GitHub Projects or similar, should contain:

- **Milestone #1** with associated tasks.
- **Projects for Milestone #1.**
- **Tasked Stories for Milestone #1**, including:
  - Sprint tasks that elaborate on the stories, with technical details.
  - These should be technical tasks required to implement one of the features.
  - Only task out stories for **Sprint #1**.  
  - Sprints #2 and #3 can be defined as we get closer.

### Sprint Planning

- The team agreed upon 2 week sprints.

- All milestones were created please see associate projects for more information.

## Stand-up Meeting Link  

- Weekly Meeting on Teams at 6pm EST
  
(https://teams.microsoft.com/l/meetup-join/19%3ameeting_YTUzN2U0ODEtZGI0Yi00NjdkLWI4YjctNDJlYzlkNzExYjk2%40thread.v2/0?context=%7b%22Tid%22%3a%22f5222e6c-5fc6-48eb-8f03-73db18203b63%22%2c%22Oid%22%3a%22e39bc5e0-3862-4cd4-927f-5c21c1da9bcf%22%7d)

