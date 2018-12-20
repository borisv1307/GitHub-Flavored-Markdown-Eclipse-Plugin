# GitHub-Flavored-Markdown-Eclipse-Plugin

## License info:

[MIT](LICENSE)

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

    We developed an open source Eclipse plugin with a unique text editor that renders Github Flavored Markdown (GFM)
    syntax in a tabbed browser, when the user saves their document. This was accomplished for a capstone Software
    Engineering course at Drexel University with the intention of creating a plugin that will be useful to many of
    Drexel's corporate partners who use Eclipse and need a text editor plugin that strictly supports GFM.

2. **Additional Functionality (which can each be toggled on and off)**

	* Pressing ctrl-space while highlighting text pops up a window with GFM syntax options the user can add to their
	highlighted text
	* Table spacing will be formatted automatically upon saving a document
	* Preferences menu to toggle features on and off and customize plugin-specific configurations

3. **How its different than other implementations**
	
	There are many Eclipse Markdown editor plugins but none strictly adhere to GFM syntax. This can be troublesome
	for companies who want their employees to be able to edit text documents in Eclipse while strictly adhering to
	GFM syntax. Additionally, our plugin runs locally and does not save/cache data in order to respect the privacy of our users and
	to meet the expectations of companies with strict privacy policy adherence. Lastly, our plugin uses Eclipse's built in 
	browser to render GFM accurately with CSS. 
	
## Installation of the plugin

[Follow these steps](https://github.com/borisv13/GitHub-Flavored-Markdown-Eclipse-Plugin/wiki/Installing)

## Building from source

[Follow these steps](https://github.com/borisv13/GitHub-Flavored-Markdown-Eclipse-Plugin/wiki/Building#building-for-deployment-wip)

[Some common build issues addressed](https://github.com/borisv13/GitHub-Flavored-Markdown-Eclipse-Plugin/wiki/Debugging.builds#debugging-the-build)
