package io.taskoverflow.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "attachments")
public class Attachment {
    int TYPE_URL = 1;
    int TYPE_PLAINTEXT = 2;
    int TYPE_BLOB = 3;

    @PrimaryKey
    @ColumnInfo(name = "attachment_id")
    public int id;
    @ColumnInfo(name = "attachment_name")
    public String name;
    @ColumnInfo(name = "attachment_type")
    public String type;

    // TYPE 1: URL and Plaintext
    @ColumnInfo(name = "attachment_plaintext")
    public String plaintext;
    // TYPE 3: Blob
    @ColumnInfo(name = "attachment_blob", typeAffinity = ColumnInfo.BLOB)
    public byte[] blob;
}
