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


template = '''
/*
{header}
*/


package {package};


// We'll need these
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;



 class {name} {
	

	// Media objects
	{media}


	// Identifiers
	{IDs}


	// Auxiliary methods
	public static Media byID(int ID) {
		// Retrieves the Media object specified by the ID
		switch (ID) {
			{byID}
			default: return null;
		}
	}


}'''	


def createClass(name, package):

	'''
	Docstring goes here

	'''
	
	with open('%s.java' % name, 'r', encoding='utf-8') as f:
		return template.format(**{
			'header': '\n'.join(' * %s' % line for line in ()),
			'package': package,
			'name': name,
 			'media': '\n\t'.join('public static final String %s = \'%s\';' % tuple(line[:-1].split('=')) for line in f if '=' in line)),
			'IDs': '',
			'byID': ''
		}


def main():
	print(createClass('sv_strings.txt'))


if __name__ == '__main__':
	main()