package simulation;

/**
 * Some hard-coded simulation data. Could be read from a configuration file some day.
 */
public class PathTypes {
	/**
	 *              high point
	 *              |
	 *              |
	 *              /
	 * low point --
	 */
	public static double[][] curveWestToNorth = {
			{0.00, 0.50},
			{0.11, 0.49},
			{0.22, 0.45},
			{0.31, 0.39},
			{0.39, 0.31},
			{0.45, 0.22},
			{0.49, 0.11},
			{0.50, 0.00}
	};

	/**
	 * low point
	 * |
	 * |
	 *  \
	 *    -- high point
	 */
	public static double[][] curveNorthToEast = {
			{0.50, 0.00},
			{0.51, 0.11},
			{0.55, 0.22},
			{0.61, 0.31},
			{0.69, 0.39},
			{0.78, 0.45},
			{0.89, 0.49},
			{1.00, 0.50}
	};

	/**
	 *    -- low point
	 *  /
	 * |
	 * |
	 * high point
	 */
	public static double[][] curveSouthToEast = {
			{1.00, 0.50},
			{0.89, 0.51},
			{0.78, 0.55},
			{0.69, 0.61},
			{0.61, 0.69},
			{0.55, 0.78},
			{0.51, 0.89},
			{0.50, 1.00}
	};
	
	/**
	 * high point --
	 *               \
	 *               |
	 *               |
	 *               low point
	 */
	public static double[][] curveWestToSouth = {
			{0.50, 1.00},
			{0.49, 0.89},
			{0.45, 0.78},
			{0.39, 0.69},
			{0.31, 0.61},
			{0.22, 0.55},
			{0.11, 0.51},
			{0.00, 0.50}
	};
	
	/**
	 * low point ---- high point
	 */
	public static double[][] westToEast = {
			{0.00, 0.50},
			{0.14, 0.50},
			{0.29, 0.50},
			{0.43, 0.50},
			{0.57, 0.50},
			{0.71, 0.50},
			{0.86, 0.50},
			{1.00, 0.50}
	};

	/**
	 * low point
	 * |
	 * |
	 * |
	 * |
	 * high point
	 */
	public static double[][] northToSouth = {
			{0.50, 0.00},
			{0.50, 0.14},
			{0.50, 0.29},
			{0.50, 0.43},
			{0.50, 0.57},
			{0.50, 0.71},
			{0.50, 0.86},
			{0.50, 1.00}
	};
}
