import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Demo {
  def main(args: Array[String]): Unit = {
    // 判断参数。111111111
    if (args.length != 1) {
      println(
        """
          |com.dahua.analyse.ProCityCount
          |缺少参数
          |inputPath
          """.stripMargin)
      sys.exit()
    }
    // 接收参数
    val Array(inputPath) = args
    var conf = new SparkConf().setMaster("yarn").setAppName("ScalaDemo01")
    var sc = new SparkContext(conf)

    val rdd: RDD[String] = sc.textFile(inputPath)

    val mf: RDD[Array[String]] = rdd.map(line=>line.split(",",-1)).filter(arr=>arr.length>=85)

    val proCity1: RDD[((String, String), Int)] = mf.map(field => {
      val pro = field(24)
      val city = field(25)
      ((pro, city), 1)
    })

    val reduceByKey: RDD[((String, String), Int)] = proCity1.reduceByKey(_+_)

    //将相同的市分到一个区
    val groupBy: RDD[(String, Iterable[((String, String), Int)])] = reduceByKey.groupBy(_._1._1)

    //排序数据
    val mapvalues: RDD[(String, List[((String, String), Int)])] = groupBy.mapValues(_.toList.sortBy(_._2).reverse)

    for(ele <- mapvalues){
      println(ele)
    }

    println("我是girone")
  }
}
