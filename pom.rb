project 'UniformNoise' do

  model_version '4.0.0'
  id 'micycle:UniformNoise:1.1'
  packaging 'jar'

  description 'Uniform Perlin Noise'

  properties(
    'UniformNoise.basedir' => '${project.basedir}',
    'polyglot.dump.pom' => 'pom.xml',
    'project.build.sourceEncoding' => 'utf-8'
  )

  plugin( :compiler, '3.8.1',
          'release' =>  '11' )
  plugin :source, '3.2.1' do
    execute_goals( 'jar-no-fork',
                   :id => 'attach-sources',
                   :phase => 'deploy' )
  end

  plugin :javadoc, '3.3.0' do
    execute_goals( 'jar',
                   :id => 'attach-javadocs',
                   :phase => 'deploy' )
  end
end
