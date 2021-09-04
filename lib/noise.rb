class UniformNoise
	attr_reader :opts
	def initialize(opts = {})
    @opts = defaults.merge(opts)
	end

  def defaults
    {seed: rand(256), octaves: 4, persistence: 0.5}
  end

	def noise(*args)
		case args.length
    when 1
    when 2
		when 3
    when 4
    else
      warn 'Only 1 to 4 dimensions supported'
		end
	end
end


generator = UniformNoise.new({octaves: 1})

puts generator.opts[:octaves]

generator.noise(1,2,3,4,5)
