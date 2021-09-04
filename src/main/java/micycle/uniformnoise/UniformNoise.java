package micycle.uniformnoise;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Perlin noise distributed uniformly between [ 0, 1 ].
 *
 * @author Michael Carleton
 *
 */
public class UniformNoise {

	private int seed;
	private int octaves;
	private double persistence;

	/**
	 * Creates a UniformNoise instance with a random seed.
	 */
	public UniformNoise() {
		this(ThreadLocalRandom.current().nextInt());
	}

	/**
	 * Creates a UniformNoise instance with the given seed.
     * @param seed
	 */
	public UniformNoise(final int seed) {
		this.seed = seed;
	}

	/**
	 * Sets the seed used by the UniformNoise instance.
     * @param seed
	 */

        public final void setSeed(final int seed) {
		this.seed = seed;
	}

	/**
	 * Sets the number of octaves to be used by the UniformNoise instance.
		 * @param octaves
	 */
	public final void setOctaves(final int octaves) {
		this.octaves = octaves;
	}

	/**
	 * Sets the persistence value to be used by the UniformNoise instance.
		 * @param ocatves

*/
	public final void setPersistence(final double persistence) {
		this.persistence = persistence;
	}

	/**
	 * Generates multi-octave 2D uniform perlin noise for the coordinates given.
	 *
	 * @param x
	 * @param y
	 * @param octaves     the number of levels of detail the perlin noise has
	 * @param persistence determines how quickly the amplitudes fall for each
	 *                    successive octave
	 * @return approximately uniformly distributed noise value between [ 0, 1 ]
	 */
	public double uniformNoise(final double x, final double y) {
		double noise = 0;
		double frequency = 1;
		double amplitude = 1;
		for (int i = 0; i < octaves; i++) {
			noise += PerlinNoiseLite.singlePerlin(seed, x * frequency, y * frequency) * amplitude;
			amplitude *= persistence;
			frequency *= 2; // lacunarity
		}
		return cdfOctaves2D(noise);
	}



	/**
	 * Generates multi-octave 3D uniform perlin noise for the coordinates given.
	 *
	 * @param x
	 * @param y
	 * @param z
	 * @param octaves     the number of levels of detail the perlin noise has
	 * @param persistence determines how quickly the amplitudes fall for each
	 *                    successive octave
	 * @return approximately uniformly distributed noise value between [ 0, 1 ]
	 */
	public double uniformNoise(final double x, final double y, final double z) {
		double noise = 0;

		double frequency = 1;
		double amplitude = 1;
		for (int i = 0; i < octaves; i++) {
			noise += PerlinNoiseLite.singlePerlin(seed, x * frequency, y * frequency, z * frequency) * amplitude;
			amplitude *= persistence;
			frequency *= 2; // lacunarity
		}
		return cdfOctaves3D(noise);
	}



	/**
	 * Fifth-degree polynomial approximation of a continuous distribution function
	 * for 2D perlin noise.
	 * <p>
	 * This approximation is derived from a set of 2D single-octave noise values.
	 *
	 * @param x raw (gaussian-like) perlin noise between [-1...1]
	 * @return approximately uniformly distributed noise value between [ 0, 1 ]
	 */
	private static double cdf2D(final double x) {
		if (x < -0.729) {
			return 0;
		}
		if (x > 0.751) {
			return 1;
		}
		return ((((1.0616674 * x - 4.779669e-2) * x - 1.5704816) * x + 2.3737413e-2) * x + 1.2176103) * x + 4.9882874e-1;
	}

	/**
	 * Fifth-degree polynomial approximation of a continuous distribution function
	 * for 3D perlin noise.
	 * <p>
	 * This approximation is derived from a set of 3D single-octave noise values.
	 *
	 * @param x raw (gaussian-like) perlin noise between [-1...1]
	 * @return approximately uniformly distributed noise value between [ 0, 1 ]
	 */
	private static double cdf3D(final double x) {
		if (x < -0.662) {
			return 0;
		}
		if (x > 0.66) {
			return 1;
		}
		return ((((2.0015578 * x + 2.8363844e-3) * x - 2.4018942) * x - 9.9932467e-4) * x + 1.4237269) * x + 5.0005216e-1;
	}

	/**
	 * Fifth-degree polynomial approximation of a continuous distribution function
	 * for 2D perlin noise.
	 * <p>
	 * This approximation is derived from a set of 2D noise values having octaves=4
	 * and persistence=0.5; the output may be increasingly non-uniform with input
	 * noise generated using a different number of octaves or persistence.
	 *
	 * @param x raw (gaussian-like) perlin noise between [-1...1]
	 * @return approximately uniformly distributed noise value between [ 0, 1 ]
	 */
	private static double cdfOctaves2D(final double x) {
		if (x < -0.897) {
			return 0;
		}
		if (x > 0.891) {
			return 1;
		}
		/*
		 * Approximation on interval [ -0.9, 0.9 ] with a polynomial of degree 5.
		 */
		return ((((5.5801775e-1 * x + 3.4876526e-3) * x - 1.102035) * x - 2.1366362e-3) * x + 1.083566) * x + 5.0009794e-1;
	}

	/**
	 * Fifth-degree polynomial approximation of a continuous distribution function
	 * for 3D perlin noise.
	 * <p>
	 * This approximation is derived from a set of 3D noise values having octaves=4
	 * and persistence=0.5; the output may be increasingly non-uniform with input
	 * noise generated using a different number of octaves or persistence.
	 *
	 * @param x raw (gaussian-like) 3D perlin noise between [-1...1]
	 * @return approximately uniformly distributed noise value between [ 0, 1 ]
	 */
	private static double cdfOctaves3D(final double x) {
		if (x < -0.796) {
			return 0;
		}
		if (x > 0.793) {
			return 1;
		}
		/*
		 * Approximation on interval [ -0.8, 0.8 ] with a polynomial of degree 5.
		 */
		return ((((9.8832232e-1 * x + 2.1932919e-3) * x - 1.6058296) * x - 1.3540642e-3) * x + 1.2490637) * x + 5.0019288e-1;
	}

}
