package kafkaproducer


import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

import scala.io.Source
object Kafkaproducer {
  def main(args: Array[String]): Unit = {
    println("Started Processing..")
    //Defining the kafka producer Properties
    val props = new Properties()
    //Producer should know where is brokers running
    props.put("bootstrap.servers", "localhost:9092")
    //Key Serializer
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    //Value Serializer
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    //create producer instance
    val kafkaProducer = new KafkaProducer[String, String](props)
    val fname = "/home/maruthi/sampledata/Samplejson2.txt"
    val fSource = Source.fromFile(fname)
    val s = fSource.getLines().foreach(x => {
      val obj = Utils.fromJson[Empdata](x)
     val obj2 = obj.copy(ename = obj.ename + "-" + obj.ename, timeStamp = System.currentTimeMillis())
//val id=obj2.timeStamp

      val str = Utils.toJson(obj2)

  val rec1 = new ProducerRecord[String, String]("mytopic", "created", str)
      kafkaProducer.send(rec1)
      println(rec1)

  val rec2 = new ProducerRecord[String, String]("mytopic", "updated",str)
       kafkaProducer.send(rec2)
            println(rec2)
      val rec3 = new ProducerRecord[String, String]("mytopic", "deleted",str)
            kafkaProducer.send(rec3)
            println(rec3)

    })

    kafkaProducer.flush()
    kafkaProducer.close()
    println("Completed Processing..")
    // println(rec)
  }

}

