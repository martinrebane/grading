#!/usr/bin/env ruby

files = ''

Dir.foreach("D:/Users/mammu/workspace/ITI0011/maria.kert/EX05/src/ee/ttu/java/albumcreation") do |file|
	next if file == '.' or file == '..'
	if File.extname(file) == '.java' then
		files += ' ' + file
	end
end

Dir.chdir("D:/Users/mammu/workspace/ITI0011/maria.kert/EX05/src/ee/ttu/java/albumcreation")
exec('gist -po' + files)