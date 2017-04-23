#!/usr/bin/env ruby

#path on lopuks ka argumendina
path = 'D:/Users/mammu/workspace/ITI0011/maria.kert/EX07/out/production/EX07/ee/ttu/java/curriculum'
mainfile = ''
Dir.chdir(path)

Dir.foreach(Dir.pwd) do |file|
	next if file == '.' or file == '..'
	if File.extname(file) == '.class' then
		IO.popen(['javap', '-public', file]) do |f|
			if f.read.include? 'public static void main(java.lang.String[])' then
			   mainfile = file
			   puts file
			end
		end
	end
	
	if mainfile != '' then
		break
	end
end