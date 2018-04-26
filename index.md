## IGGI AI based Game Design (Part II)

**Summary:** AI-based Game Design (Part II) extends the principles of game Design studied in Part I with the latest Artificial Intelligence techniques. This part of the module will cover state of the art AI methods that can be used for automatic game tuning, game design, and testing using AI agents and methods. During this module, you will learn these techniques, design your own game and participate in an AI hack. Finally, you will write all your work and experiments in a scientific paper and receive feedback in order to help you submit it to a conference. 

## Outline

The theme of this part of the course is AI Informed Game Design. The main aim is use AI to make better games, though attention will also be given to how we can design games to test important aspects of AI. What "better" means in this context can be hard to pin down, and in everyday terms is anyway a subjective measure. To make this more objective we'll consider aspects of player experience that can be measured and optimised. Since getting human players to play through games can be expensive and time consuming, we'll look at using software agents to automatically play test games.

A key aspect of the course is to consider how the design space of a class of games can be defined using appropriate data or program structures and then explored using automated search algorithms. Depending on how generally the search space is defined this approach can either be used to generate novel games, or it can be used to find the best set of parameters for a particular game.

The course will be taught with a mixture of lectures, labs (including game AI competitions), group work and invited talks.

## Main topics

**Game Design Spaces:** Methods for parameterising a game and building a search space for:
 - game rules
 - vehicle physics
 - weapon systems
 - input controls
 - game views
 - level designs
 
 
 **Toward quick and easy game AI:** The relevance of this is to generate a diverse set of agents to automatically play-test a game. This section will consider ways to quickly generate a number of reasonable performance AI players with relatively little effort. You may have already have covered many or even all the techniques below, but much can be learned by applying them to different games: there's still much to be learned regarding which methods work best and why:
 - Monte Carlo Tree Search
 - Rolling Horizon Evolutionary Algorithms
 - Video Game Description Language (VGDL) and General Video Game AI

To study aspects of game design spaces and game AI we'll use some of the following (you may also be able to use your own examples)
 - Flappy Bird
 - "Space battle"
 - Asteroids
 - Planet wars
 
Experimental work will be based on a version of Planet Wars and also GVGAI (all written in Java).

For some of the games used, reasonably efficient source code will be provided that can be run headless thousands of times faster than real time, enabling simulation-based search to be used to implement AI controllers.
To test the various AI approaches mini-competitions will be run to see which team can generate the best performing AI, or the AI which best optimises some aspect of player experience.

**Defining and measuring aspects of player experience:** We'll run a workshop on simple and more complex aspects of player experience which can be measured, and expriment with how sensitive these are to changes in the design of a game.

**Game A versus Game B:** You will work towards making a comparison between two (hopefully interestingly different) alternative games drawn . The experience that some software agents have playing these games will then be measured, with the aim of predicting how this relates to human player experience.

## Materials

**Lecture Slides: ** 
 - A
 - B
 
**Labs: ** 
 - A
 - B

**Extra Materials: **

- Suggested paper outline: PDF [LINK]
- Papers should be prepared in the IEEE standard double-column format. Word and LaTeX templates are available on the website of CEEC2018 [LINK]. Click [LINK] to download the LaTex paper template with outline. 
  - Group Presentation Marking Form: PDF [LINK]
  - For some general advice on scientific peer review, the links below are useful. Please keep in mind that not all the points apply to the peer review part of this course:
  - [http://www.jmlr.org/reviewing-papers/p92-parberry.pdf] http://www.jmlr.org/reviewing-papers/p92-parberry.pdf 
  - [https://violentmetaphors.com/2013/12/13/how-to-become-good-at-peer-review-a-guide-for-young-scientists/] https://violentmetaphors.com/2013/12/13/how-to-become-good-at-peer-review-a-guide-for-young-scientists/ 
- We encourage groups to revise their papers after the peer feedback and submit to a conference such as CEEC 2018 [LINK] (Games special session).

## Assessment

Students are advised to work in groups (ideal size 3 or 4).

**Deliverables: **

The deliverables are as follows. They should be packaged into a single .zip file and send to the instructors via email.
  - Report in the form of a short (4-6 pages) draft conference paper. The paper should include or be accompanied by a brief statement of the contribution of each author.
  - Peer-review comments on the paper (peer review is by course participants). 
  - Code for two versions of a game drawn from a game design space 
  - A video showing the games in action 
  - Data (game logs etc) underlying the results in the paper 
  - Presentation slides

**Weighting: **

 - Game AI Hack: 10%
 - Group Presentation: 10%
 - Peer reviews: 20%
 - Final version of the paper: 60%



```### Markdown

Markdown is a lightweight and easy-to-use syntax for styling your writing. It includes conventions for

```markdown
Syntax highlighted code block

# Header 1
## Header 2
### Header 3

- Bulleted
- List

1. Numbered
2. List

**Bold** and _Italic_ and `Code` text

[Link](url) and ![Image](src)


For more details see [GitHub Flavored Markdown](https://guides.github.com/features/mastering-markdown/).

### Jekyll Themes

Your Pages site will use the layout and styles from the Jekyll theme you have selected in your [repository settings](https://github.com/GAIGResearch/AIGD2/settings). The name of this theme is saved in the Jekyll `_config.yml` configuration file.

### Support or Contact

Having trouble with Pages? Check out our [documentation](https://help.github.com/categories/github-pages-basics/) or [contact support](https://github.com/contact) and we’ll help you sort it out.
