package svs.amc.tomz.elem;

/**
 * Created by serpye on 6/12/17.
 */
public class SparkList
{
/*::|		FIELD		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public static Spark aneSellie;
	public static Spark aneLast;

	public static short size = 3;
	/*::|		F/P			:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public static void add(Spark Spark)
	{
		if (aneSellie == null)
			aneSellie = aneLast = Spark;

		else
		{
			Spark.listNext = aneSellie;
			aneSellie.listPrevious = Spark;
			aneSellie = Spark;
		}
	}

	public static void eliminate(Spark Spark)
	{
		if (Spark == aneSellie && Spark == aneLast)
		{
			aneSellie = aneLast = null;
		}

		else if (Spark == aneSellie)
		{
			aneSellie = aneSellie.listNext;
			aneSellie.listPrevious = null;
			Spark.listNext = null;
		}

		else if (Spark == aneLast)
		{
			aneLast = aneLast.listPrevious;
			aneLast.listNext = null;
			Spark.listPrevious = null;
		}

		else
		{
			Spark.listPrevious.listNext = Spark.listNext;
			Spark.listNext.listPrevious = Spark.listPrevious;

			Spark.listNext = null;
			Spark.listPrevious = null;
		}
	}

	public static void clear()
	{
		if (aneSellie == null)
			return;

		Spark curr = aneSellie;
		Spark next = curr.listNext;

		while (next != null)
		{
			curr.listNext = null;
			next.listPrevious = null;
			curr = next;
			next = next.listNext;
		}

		aneSellie = aneLast = null;
	}
}
