package gl.open.com.contact.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;

import gl.open.com.contact.Bean.Contact;

/**
 * Created by Zhu TingYu on 2019/4/1.
 */

public class GetPhone {

    //获取通讯录的方法
    public static ArrayList<Contact> getPhone(Context context) {
        /**
         * 1.系统给了我们一个接口访问
         * 2.3.4.5也是查询条件，这我们并不需要
         * 并且返回一个Cursor类型的参数
         */
        Cursor query = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        //创建一个对象进行储存
        ArrayList<Contact> List = new ArrayList<>();
        //我们获取到这些信息之后遍历出来
        while (query.moveToNext()) {
            //获取名字就需要Phone.DISPLAY_NAME
            Contact contact  = new Contact();
            contact.setName(query.getString(query.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
            //获取num字段需要Phone.NUMBER
            contact.setTelNumbel(query.getString(query.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
            List.add(contact);
        }
        return List;
    }

}
