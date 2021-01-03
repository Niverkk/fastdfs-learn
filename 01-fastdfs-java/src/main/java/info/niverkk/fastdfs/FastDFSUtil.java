package info.niverkk.fastdfs;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

public class FastDFSUtil {

    public static void main(String[] args) {
       download();
    }

    /**
     * 文件上传
     */
    public static void upload() {
        TrackerServer ts=null;
        StorageServer ss=null;
        try {
            //读取FastDFS的配置文件用于将所有的tracker的地址读取到内存中
            ClientGlobal.init("fdfs_client.conf");
            //检查加载配置效果
            System.out.println("ClientGlobal.configInfo(): " + ClientGlobal.configInfo());

            TrackerClient tc=new TrackerClient();
            ts=tc.getConnection();
            ss=tc.getStoreStorage(ts);
            //定义Storage的客户端对象，需要使用这个对象来完成具体的文件上传 下载和删除操作
            StorageClient sc=new StorageClient(ts,ss);
            /**
             * 文件上传
             * 参数 1 为需要上传的文件的绝对路径
             * 参数 2 为需要上传的文件的扩展名
             * 参数 3 为文件的属性文件通常不上传
             * 返回一个String数组 这个数据对我们非常总要必须妥善保管建议存入数据库
             * 数组中的第一个元素为文件所在的组名
             * 数组中的第二个元素为文件所在远程路径名
             */
            String[] result= sc.upload_file("C:\\Users\\Niverkk\\Desktop\\error.log","log",null);

            for(String str:result){
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        } finally {
            if(ss!=null){
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(ts!=null){
                try {
                    ts.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }


    }

    /**
     * 下载文件
     */
    public static void download() {
        TrackerServer ts=null;
        StorageServer ss=null;
        try {
            //读取FastDFS的配置文件用于将所有的tracker的地址读取到内存中
            ClientGlobal.init("fdfs_client.conf");
            TrackerClient tc=new TrackerClient();
            ts=tc.getConnection();
            ss=tc.getStoreStorage(ts);
            //定义Storage的客户端对象，需要使用这个对象来完成具体的文件上传 下载和删除操作
            StorageClient sc=new StorageClient(ts,ss);
            /**
             * 文件下载
             * 参数1 需要下载的文件的组名
             * 参数2 需要下载文件的远程文件名
             * 参数3 需要保存的本地文件名
             * 返回一个int类型的数据 返回0 表示文件下载成功其它值表示文件在下载失败
             */
            String groupName="group1";
            String remoteFilename="M00/00/00/wKhMgF_xttOAQmxZAAAmemW_zO0048.log";
            String localFilename="C:\\Users\\Niverkk\\Desktop\\error-download.log";
            int result=sc.download_file(groupName,remoteFilename,localFilename);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        } finally {
            if(ss!=null){
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(ts!=null){
                try {
                    ts.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 文件删除
     */
    public static void delete() {
        TrackerServer ts=null;
        StorageServer ss=null;
        try {
            //读取FastDFS的配置文件用于将所有的tracker的地址读取到内存中
            ClientGlobal.init("fdfs_client.conf");
            TrackerClient tc=new TrackerClient();
            ts=tc.getConnection();
            ss=tc.getStoreStorage(ts);
            //定义Storage的客户端对象，需要使用这个对象来完成具体的文件上传 下载和删除操作
            StorageClient sc=new StorageClient(ts,ss);
            /**
             * 文件删除
             * 参数1 需要删除的文件的组名
             * 参数2 需要删除文件的远程文件名
             * 返回一个int类型的数据 返回0 表示文件删除成功其它值表示文件在删除失败
             */
            String groupName="group1";
            String remoteFilename="M00/00/00/wKhMgF_xttOAQmxZAAAmemW_zO0048.log";
            int result=sc.delete_file(groupName,remoteFilename);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        } finally {
            if(ss!=null){
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(ts!=null){
                try {
                    ts.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }


    }

}
