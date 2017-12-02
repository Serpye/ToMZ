package svs.amc.tomz.elem;
/**
 * Created by serpye on 7/1/17.
 */
public abstract class Spark
{
/*::|		FIELD		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public Spark listNext;
	public Spark listPrevious;

	public float x;
	public float y;

	public float size;

//==|	Partical Extended
	public float sqcSparkXY[][];

	public static Object mutex = new Object();
/*::|		CONSTRUCTOR		:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
/*::|		F/P			:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~*/
	public abstract void dameuteDirectly();

	public static void dameute()
	{
		Spark spark = SparkList.aneSellie;
		while (spark != null){
			spark.dameuteDirectly();

			spark = spark.listNext;
		}
	}

	public abstract boolean moveDirectly();

	public static void move()
	{
		Spark spark = SparkList.aneSellie;

		while (spark != null)
		{
			if (spark.moveDirectly())
			{
				Spark next = spark.listNext;

				SparkList.eliminate(spark);

				spark = next;
				continue;
			}

			spark = spark.listNext;
		}
	}
}
