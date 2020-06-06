import java.io.{FileReader, BufferedReader}
import scala.collection.mutable.{MutableList, Map}

object CSVReader {
    def main(args: Array[String]) {
        val file = new FileReader("./data/iris.data")
        val reader = new BufferedReader(file)
        var delimiter = ","
        try {
            val alldata = new MutableList[Array[String]]
            var line:String = null
            while({line = reader.readLine(); line} != null) {
                if(line.length != 0){
                    var splitline: Array[String] = line.split(delimiter)
                        .map(_.trim)
                    alldata += splitline
                }
            }
            val labels = MutableList("sepal length", "sepal width",
                "petal length", "petal width", "class")
            val labelled = labels.zipWithIndex.map {
                case (label, index) => label -> alldata.map(x => x(index))
            }
            val csvdata: Map[String, MutableList[String]] = Map()
            for(pair <- labelled){
                csvdata += pair
            }
        }
        finally {
            reader.close()
        }
    }
}
