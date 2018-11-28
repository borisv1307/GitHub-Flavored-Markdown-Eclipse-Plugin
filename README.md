# GitHub-Flavored-Markdown-Eclipse-Plugin

## License info:

### Drexel Software Engineering Capstone (Graduate)

### Contributors:
* Professor: Boris Valerstein
* Neil Castelino
* Kristófer Másson
* Jiafeng Tian
* Andrew Williams
* Ibukunoluwa Fatoki

## Project Overview:

1. **What we did and why we did it**

    We developed an open source eclipse plugin with a unique text editor that renders Github Flavored Markdown (GFM)
    syntax in a tabbed browser, when the user saves their document. This was accomplished for a capstone Software
    Engineering course at Drexel University with the intention of creating a plugin that will be useful to many of
    Drexel's corporate partners who use eclipse and need a text editor plugin that strictly supports GFM.

2. **Additional Functionality (which can each be toggled on and off)**

	* Pressing ctrl-space while highlighting text pops up a window with GFM syntax options the user can add to their
	highlighted text
	* incorrect table spacing will be corrected automatically upon saving a document

3. **How its different than other implementations**
	
	There are many eclipse markdown editor plugins but none strictly adhere to GFM syntax. This can be troublesome
	for companies who want their employees to be able to edit text documents in eclipse while strictly adhering to
	GFM syntax. Additionally, our plugin runs locally and does not save/cache data in order to respect the privacy of our users and
	to meet the expectations of companies with strict privacy policy adherence. Lastly, our plugin uses eclipse's built in 
	browser to render GFM accurately with CSS. 
	
## Install

To pull submodules in the project do

```
git pull --recurse-submodules
```
