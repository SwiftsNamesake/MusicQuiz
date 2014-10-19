#
# ClassBuilder.py
# Automatic creation of Java classes
#
# Jonatan H Sundqvist
# October 18 2014
#

# TODO | -
#        -

# SPEC | -
#        -


from time import localtime, strftime
from os.path import basename, splitext
from os import listdir


template = '''
/*
 *
{header}
 *
 */


package {package};


// We'll need these
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;



 class {name} {{
	

	// Media objects
	{media}


	// Identifiers
	{IDs}


	// Auxiliary methods
	public static Media byID(int ID) {{
		// Retrieves the Media object specified by the ID
		switch (ID) {{
			{byID}
			default: return null;
		}}
	}}


}}'''[1:] # Strip initial newline (yes, I'm a lazy bastard)


def createClass(name, package, author, resources):

	'''
	Docstring goes here

	'''
	
	with open('%s.java' % name, 'r', encoding='utf-8') as f:
		# TODO: Align equal signs
		# TODO: Relative paths
		return template.format(**({
			'header': '\n'.join(' * %s' % line for line in ('%s.java' % name, 'Contains resources for %s' % package, '', author, strftime('%a %c', localtime()) )),
			'package': package,
			'name': name,
 			'media': '\n\t'.join('public static final Media %s = Utilities.loadSound(\'%s\');' % (splitext(basename(res))[0].replace(' ', '_'), res) for res in resources),
			'IDs': '\n\t'.join('public static final int %s = %d;' % (splitext(basename(name))[0].upper().replace(' ', '_'), n) for n, name in enumerate(resources)),
			'byID': '\n\t\t\t'.join('case %s: return %s;' % (line, line.upper()) for line in map(lambda res: splitext(basename(res))[0], resources))
		}))


def main():
	# print(createClass('sv_strings.txt'))
	print(createClass('R', 'MusicQuiz', 'Jonatan H Sundqvist', tuple(filter(lambda path: splitext(path)[1].lower() in ('.wav', '.mp3'), listdir('resources')))))


if __name__ == '__main__':
	main()