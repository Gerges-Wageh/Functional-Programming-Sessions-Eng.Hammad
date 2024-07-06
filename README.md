# Functional Programming Examples by Eng. Mohammed Hammad in Java
### "Functional Programming is all about making programming soild and robust as mathematics and should be viewed from that point"

Welcome to the repository that contains all the examples discussed in the Functional Programming sessions by Eng. Mohammed Hammad. This repository serves as a comprehensive resource for understanding various functional programming concepts through practical examples.

## Table of Contents

- [Introduction](#introduction)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Sessions](#sessions)
  - [Session 1: Introduction To Declarative Programming](#Session-1-introduction-to-declarative-programming)
  - [Session 2: Pipeline Concept](#session-2-pipeline-concept)
  - [Session 3: Higher-Order Functions](#session-3-higher-order-functions)
  - [Session 4: Comprehensive Example](#session-4-comprehensive-example)
  - [Session 5: Function Composition](#session-5-function-composition)
  - [Session 6: Comprehensive Real Life Problem](#session-6-comprehensive-real-life-problem)
  - [Session 7: Closures](#session-7-closures)
  - [Session 8: LINQ or Java Stream API Under The Hood](#session-8-linq-or-java-stream-api-under-the-hood)

- [Acknowledgments](#acknowledgments)

## Introduction

This repository contains a collection of valuable functional programming examples discussed during the sessions by Eng. Mohammed Hammad. The aim is to provide clear and concise examples that illustrate key concepts in functional programming, making it easier for developers to grasp and apply these principles in their own projects.\
You can find the complete recorded sessions via this [Link](https://www.youtube.com/playlist?list=PLpbZuj8hP-I6F-Zj1Ay8nQ1rMnmFnlK2f)
## Prerequisites

To run the examples in this repository, you will need:

- [Java Development Kit (JDK) 11 or higher](https://www.oracle.com/java/technologies/javase-downloads.html)
- An Integrated Development Environment (IDE) such as [IntelliJ IDEA](https://www.jetbrains.com/idea/) or [Eclipse](https://www.eclipse.org/)

## Installation

Clone this repository to your local machine using the following command:

```bash
git clone https://github.com/your-username/functional-programming-examples.git
```

Navigate to the project directory:

```bash
cd Functional-Programming-Sessions-Eng.Hammad
```

## Usage

To run the examples, open the project in your preferred IDE and execute the main class for each example. Each example is contained within its own package for ease of navigation.

## Sessions


<a name="Session-1-introduction-to-declarative-programming"></a>
## Session 1: Introduction To Declarative Programming
In this session example we discussed an OOP style code with global states to contrast how bad the idea of global states.

        
<a name="session-2-pipeline-concept"></a>
### Session 2: Pipeline Concept
In this session we introduced the pipeline concept through simple example solved once imperative and once declarative.                        


<a name="session-3-higher-order-functions"></a>
### Session 3: Higher-Order Functions
In this session we introduced the idea of "Funtions As Data", and illustrated how to store a function in a delegate (Functional Interface in Java) and deal with them the way you deal with data              (i.e. storing them in different data structures, passing them through functions, returning them from functions).\
We illustrated the concept of "Higher-Order function" via an example where we used a higher order function that accepts a function as a parameter to calculate some data internally without expose            this as a separate step in the pipeline

        
        
<a name="session-4-comprehensive-example"></a>
## Session 4: Comprehensive Example
In this session we discussed an example that may change your way of thinking about programming, the example illustrates the idea of looping over a list of functions and pumping them through the                 pipeline as we deal with data!\
The solution constructed two pipelines, the first pumping data through a function which internally pumping a list of functions though another pipeline to apply them on the same piece of data!



<a name="session-5-function-composition"></a>
## Session 5: Function Composition
This session discussed a very critical concept in functional programming which is Function Composition. We see the power of function composition in the session 6 example. 



<a name="session-6-comprehensive-real-life-problem"></a>
## Session 6: Comprehensive Real Life Problem
In this session we solved a huge real life business problem, an outstanding example to cover all the functional programming concepts so far. We saw how Function Composition is a powerful property to develop a testble, extendable and robust code for a very complex problem while maintaning the granularity.


<a name="session-7-closures"></a>
## Session 7: Closures
In this session we discussed the concept of closures and saw the benifit of returning a function that maintaing state inside from another function.


<a name="session-8-linq-or-java-stream-api-under-the-hood"></a>
## Session 8: LINQ or Java Stream API Under The Hood
This is the most interesting part, when you understand the whole magic, and how things work under the hood.

