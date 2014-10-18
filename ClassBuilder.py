def createClass(fn):
	template = '''
class R {

	%s

}
'''
	
	with open(fn, 'r', encoding='utf-8') as f:
		return template % '\n\t'.join('public static final String %s = \'%s\';' % tuple(line[:-1].split('=')) for line in f if '=' in line)


def main():
	print(createClass('sv_strings.txt'))


if __name__ == '__main__':
	main()