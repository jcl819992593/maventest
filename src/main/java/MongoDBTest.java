import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/7/6.
 * <p>
 * MongoDB练习
 */
public class MongoDBTest {

    public static void main(String[] args) {

        //mongoDBTest1();
        //mongoDBTest2();
       // mongoDBTest3();
        //mongoDBTest4();
        mongoDBTest5();

    }

    //简单的取值操作
    public static void mongoDBTest1() {

        MongoDatabase mongoDatabase = getMongoDatabase();
        //获取集合
        MongoCollection<Document> collection= mongoDatabase.getCollection("ld");
        //查询出集合里面的内容
        FindIterable<Document> documents = collection.find();
        //得到迭代器
        MongoCursor<Document> iterator = documents.iterator();
        //循环取值
        while (iterator.hasNext()) {
            Document document = iterator.next();
            String name = (String) document.get("姓名");
            String addr = (String) document.get("住址");
            System.out.println("姓名：" + name + "-----" + "住址：" + addr);
        }


    }

    //创建集合
    public static void mongoDBTest2() {

        MongoDatabase mongoDatabase = getMongoDatabase();
        mongoDatabase.createCollection("ld");

        System.out.println("集合创建成功！");
    }

    //插入文档
    public static void mongoDBTest3() {
        MongoDatabase mongoDatabase = getMongoDatabase();
        MongoCollection<Document> collection = mongoDatabase.getCollection("ld");//选择集合，选中要添加数据的集合
        System.out.println("集合获取成功！");
        //创建文档
        Document document = new Document();
        document.append("_id",2);
        document.append("姓名", "阿三");
        document.append("性别", "男");
        document.append("住址", "北京");
        document.append("电话", "654321");

        //创建一个集合并将document放入集合中
        List<Document> list = new ArrayList<Document>();
        list.add(document);
        //将创建的集合放入到MongoDB集合中
        collection.insertMany(list);
        System.out.println("添加数据成功");

    }

    //修改文档内容
    public static void mongoDBTest4(){
        MongoDatabase mongoDatabase = getMongoDatabase();
        MongoCollection<Document> collection = mongoDatabase.getCollection("ld");
        collection.updateOne(Filters.eq("_id","num01"),new Document("$set",new Document("姓名","张三")));
        //FindIterable<Document> documents = collection.find();
        //MongoCursor<Document> iterator = documents.iterator();
        System.out.println("文档修改成功！");
    }

    //删除文档
    public static void mongoDBTest5(){
        MongoDatabase mongoDatabase = getMongoDatabase();
        MongoCollection<Document> collection = mongoDatabase.getCollection("ld");
        collection.deleteOne(Filters.eq("_id",2));
        System.out.println("删除成功");
    }

    //MongoDatabase工具 获取MongoDB连接
    public static MongoDatabase getMongoDatabase() {
        //连接到服务
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        //连接到数据库
        MongoDatabase database = mongoClient.getDatabase("mongo");
        return database;
    }

}
