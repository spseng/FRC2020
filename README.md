# FRC2020
St. Paul's School FIRST Robotics Team 1512 software for the 2020 competition.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

The FRC 2020 Java/VS Code WPI [screensteps](https://docs.wpilib.org/en/stable/index.html) have been completed in their entirety.

For Driver's Station laptop, complete screensteps FRC Game Tools installation as well.

### Installing

Install [git](https://git-scm.com/downloads)

Go through [CTRE installation steps](https://phoenix-documentation.readthedocs.io/en/latest/ch05_PrepWorkstation.html) and [add CTRE Libraries to your Project](https://phoenix-documentation.readthedocs.io/en/latest/ch05a_CppJava.html)

That's it, you are ready to code!

### Preparing the Robot

Follow [screensteps](https://docs.wpilib.org/en/stable/index.html) instructions for:
* Updating roboRIO firmware with roboRIO Imaging Tool
* Updating Radio firmware with Radio Configuration Utility

Then update Talon motor controller firmware using Phoenix Tuner

When deploying code to the Robot for the first time, sometimes Gradlew will throw an error. Running it with an internet connection will allow it to update and then it should be fine.

## Using RaspberryPi

To communicate with roboRIO, use [PyNetworkTables docs](https://robotpy.readthedocs.io/projects/pynetworktables/en/stable/examples.html)

For Vision Processing, [GRIP](https://wpiroboticsprojects.github.io/GRIP/#/) is useful for generating code to filter colors and find lines or contours. It is occasionally prone to crashing.

GRIP code and most computer vision applications require OpenCV to be installed. The installation process on a RaspberryPi can be found [here](https://www.pyimagesearch.com/2019/09/16/install-opencv-4-on-raspberry-pi-4-and-raspbian-buster/). It can take a while.

## Contributing

To contribute, follow the following steps

First ensure you are not on the master branch by running
```
git branch
```
If you are on the branch that you would like to make edits on, skip the following step, otherwise run
```
git checkout -b *name of the branch you want to create*
```
Now that you are on your working branch, you can make edits! Upon making edits, follow these steps to create a pull request

```
git add --all
```
This stages all your changes to be committed
```
git commit -m "*commit message you want*"
```
This commits your changes (preps it to be pushed to a remote - ie online)
```
git push origin *name of branch you are working on*
```
This pushes your changes to the remote and if the branch does not exist on the remote it creates it

Next go [here](https://github.com/spseng/FRC2020/branches) and click the "pull request" button


## Important Information about This Year's System

### The world of motor control
This year we are using CAN Talons to control various motors on the robot. For autonomous mode we have decided to use quadrature magnetic encoders to precisely control the speed of these motors.

Information on how to use these encoders in java:
To determine what value to pass to the talon for setting the desired encoder location, refer to this equation:
```
(Encoder Value) = ((Desired RPM - Revolutions per Minute) * 4096) / 600)
```
This was derived from the given knowledge that 4096 "units" (ie a change in position) per 100ms is equal to 1 revolution per 100 ms

This information is useful as the robot can be told to go a certain distance, knowing that:
```
Distance = (diameter of tire) * pi * minutes * RPM*
```
Note that this equation can be rearranged to solve for distance, time, and RPM. For example, if you knew how far you wanted the robot to go and how fast you wanted the robot to accomplish the movement:
```
RPM = (desired Distance) / ((diameter of tire) * pi * (desired time))
```

## Authors

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.
