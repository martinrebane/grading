#!/usr/bin/env ruby

files = ''
#path on lopuks argumendina
path = 'D:/Users/mammu/workspace/ITI0011/maria.kert/EX05/src/ee/ttu/java/albumcreation'

Dir.foreach(path) do |file|
	next if file == '.' or file == '..'
	if File.extname(file) == '.java' then
		files += ' ' + file
	end
end

Dir.chdir(path)
exec('gist -po' + files)