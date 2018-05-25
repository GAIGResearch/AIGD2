# IGGI AI based Game Design (Part II)

## Summary 

AI-based Game Design (Part II) extends the principles of game Design studied in Part I with the latest Artificial Intelligence techniques. This part of the module will cover state of the art AI methods that can be used for automatic game tuning, game design, and testing using AI agents and methods. During this module, you will learn these techniques, design your own game and participate in an AI hack. Finally, you will write all your work and experiments in a scientific paper and receive feedback in order to help you submit it to a conference. 

## Outline

The theme of this part of the course is AI Informed Game Design. The main aim is use AI to make better games, though attention will also be given to how we can design games to test important aspects of AI. What "better" means in this context can be hard to pin down, and in everyday terms is anyway a subjective measure. To make this more objective we'll consider aspects of player experience that can be measured and optimised. Since getting human players to play through games can be expensive and time consuming, we'll look at using software agents to automatically play test games.

A key aspect of the course is to consider how the design space of a class of games can be defined using appropriate data or program structures and then explored using automated search algorithms. Depending on how generally the search space is defined this approach can either be used to generate novel games, or it can be used to find the best set of parameters for a particular game.

The course will be taught with a mixture of lectures, labs (including game AI competitions) and group work.

## Schedule

**Week 1 (June 4th - June 8th)**

<img src="img/Week1.png" alt="Week1" class="inline"/>

**Week 2 (June 11th - June 15th)**

<img src="img/Week2.png" alt="Week2" class="inline"/>



## Main topics

**Game Design Spaces:** Methods for parameterising a game and building a search space for:
 - game rules and parameters
 - input controls
 - vgdl
 - game design
 - level designs
 
 
 **Toward quick and easy game AI:** The relevance of this is to generate a diverse set of agents to automatically play-test a game. This section will consider ways to quickly generate a number of reasonable performance AI players with relatively little effort. You may have already have covered many or even all the techniques below, but much can be learned by applying them to different games: there's still much to be learned regarding which methods work best and why:
 - Monte Carlo Tree Search
 - Rolling Horizon Evolutionary Algorithms
 - Video Game Description Language (VGDL) and General Video Game AI
 - N-Tuple Bandit Evolutionary Algorithms

To study aspects of game design spaces, and also for your experimental work, we'll use a version of Planet Wars and GVGAI games (all written in Java). You may also be able to use your own examples.

For some of the games used, reasonably efficient source code will be provided that can be run headless thousands of times faster than real time, enabling simulation-based search to be used to implement AI controllers. To test the various AI approaches mini-competitions will be run to see which team can generate the best performing AI, or the AI which best optimises some aspect of player experience.

**Defining and measuring aspects of player experience:** We'll run a lab on simple and more complex aspects of player experience which can be measured, and expriment with how sensitive these are to changes in the design of a game.

**Game A versus Game B:** One of your options is to work on a comparison between two (hopefully interestingly different) alternative games drawn. The experience that some software agents have playing these games will then be measured, with the aim of predicting how this relates to human player experience.

## Materials

**Lecture Slides:** 
 - <a href="https://github.com/GAIGResearch/AIGD2/blob/master/lectures/2.%20Introduction%20(Long).pptx?raw=true">Introduction</a>
 - Coming soon.
 
**Labs: ** 
 - Coming soon.

**Extra Materials:**

- Suggested paper outline: <a href="https://github.com/GAIGResearch/AIGD2/blob/master/utils/PaperOutline.pdf?raw=true">PDF</a>
- Papers should be prepared in the AIIDE-2018 standard double-column format (6 pages of content plus 1 for references). Word and LaTeX templates are available <a href="http://www.aaai.org/Publications/Templates/AuthorKit18.zip">here</a>.
  - Group Presentation Marking Form <a href="https://github.com/GAIGResearch/AIGD2/blob/master/utils/GroupPresentationForm.docx?raw=true">docx</a>
  - For some general advice on scientific peer review, the links below are useful. Please keep in mind that not all the points apply to the peer review part of this course:
  - [http://www.jmlr.org/reviewing-papers/p92-parberry.pdf] (http://www.jmlr.org/reviewing-papers/p92-parberry.pdf)
  - [https://violentmetaphors.com/2013/12/13/how-to-become-good-at-peer-review-a-guide-for-young-scientists/] (https://violentmetaphors.com/2013/12/13/how-to-become-good-at-peer-review-a-guide-for-young-scientists/)
- We encourage groups to revise their papers after the peer feedback and submit to a conference such as [AIIDE-2018] (https://sites.google.com/ncsu.edu/aiide-2018/home) or [CEEC 2018] (https://ceec.uk/) (Games special session).


## Assessment

Students are advised to work in groups (size 2, 3 or 4).

**Deliverables:**

The deliverables are as follows. They should be packaged into a single .zip file and send to the instructors via email.
  - Report in the form of a short (4-6 pages) draft conference paper. The paper should include or be accompanied by a brief statement of the contribution of each author.
  - Peer-review comments on the paper (peer review is by course participants). 
  - Code of the AI Hack and paper works
  - A video showing the games in action 
  - Data (game logs etc) underlying the results in the paper
  - Presentation slides

**Weighting:**

 - Game AI Hack: 10%
 - Group Presentation: 10%
 - Peer reviews: 20%
 - Final version of the paper: 60%
