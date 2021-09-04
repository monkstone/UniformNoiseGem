# frozen_string_literal: true

lib = File.expand_path('../lib', __FILE__)
$LOAD_PATH.unshift(lib) unless $LOAD_PATH.include?(lib)
require 'uniform_noise/version'

Gem::Specification.new do |spec|
  spec.name = 'uniform_noise'
  spec.version = UniformNoise::VERSION
  spec.extra_rdoc_files = %w[README.md LICENSE.md]
  spec.summary = %q[Updated and extended uniform_noise libraries for JRubyArt and propane]
  spec.description =<<-EOS
  uniform_noise java libraries wrapped in a rubygem. Compiled and tested with JRubyArt-2.4 and processing-3.4
  EOS
  spec.licenses = %w(MIT LGPL-3.0)
  spec.authors = %w(Karsten\ Schmidt Martin\ Prout)
  spec.email = 'mamba2928@yahoo.co.uk'
  spec.homepage = 'http://ruby-processing.github.io/toxicgem/'
  spec.files = `git ls-files`.split($INPUT_RECORD_SEPARATOR)
  spec.files << 'lib/uniform_noise.jar'
  spec.require_paths = ['lib']
  spec.add_development_dependency 'rake', '~> 12.3', '>= 12.3.0'
end
