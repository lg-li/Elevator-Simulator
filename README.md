# Elevator Simulator #

## System Design ##
This system is a kind of closed-loop control system. The data flow transit between different types of classes in this system is shown in Figure.1 below.

![Figure 1. Data flow graph of the elevator system](https://github.com/Cheelem/ElevatorSimulator/blob/master/images/flow_diagram.png)

Observer pattern is applied on simulation of sensors and elevator controller, as the change of sensor should immediately be known by the elevator controller. Meanwhile, this pattern makes it easier to extend the type of sensors for the controller.

State pattern is applied on the Elevator Controller class to define the logic and transition rules of different states. This pattern improves the maintainability and extensibility of the states of elevator when add some functionality like elevator maintenance mode. 
The state diagram of Elevator Controller is shown in Figure 2.
 
![Figure 2. State diagram of Elevator Controller](https://github.com/Cheelem/ElevatorSimulator/blob/master/images/state_diagram.png)

To simulate the external environment, a class called Environment has been added to the design. This class defined a physical interface for the actuator classes (motors) to call, and it also perform a delay in the operation to simulate the real situation in the physical world.
Singleton pattern is applied to this class, as there will be only one environment for an independent elevator controller. With singleton pattern, the actuator classes can directly access the environment class and environment won’t be referenced by the controller class.

The class diagram is shown in Figure 3.
 
![Figure 3. Class diagram of the system](https://github.com/Cheelem/ElevatorSimulator/blob/master/images/class_diagram.png)


## To Run ##

This is a JavaFX application, and the main class is at cn/edu/neu/elevator/Main.java

## Contributors ##
Lingen Li, Chenming Chang, Jingqi Chen

## Lisence ##
This project license is
[MIT](http://opensource.org/licenses/MIT).
