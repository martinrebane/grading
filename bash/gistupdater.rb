#!/usr/bin/env ruby

#esimene argument on update'itava gisti id

files = ''
#path on lopuks ka argumendina
path = 'D:/Users/mammu/workspace/ITI0011/maria.kert/EX05/src/ee/ttu/java/albumcreation'
gist_id = ARGV[0]

Dir.foreach(path) do |file|
	next if file == '.' or file == '..'
	if File.extname(file) == '.java' then
		files += ' ' + file
	end
end

Dir.chdir(path)
exec('gist -ou ' + gist_id + files)