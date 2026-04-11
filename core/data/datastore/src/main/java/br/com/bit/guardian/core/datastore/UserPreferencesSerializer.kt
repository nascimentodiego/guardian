package br.com.bit.guardian.core.datastore

import androidx.datastore.core.Serializer
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.InputStream
import java.io.OutputStream

object UserPreferencesSerializer : Serializer<UserPreferences> {
    override val defaultValue: UserPreferences = UserPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserPreferences {
        return try {
            val dis = DataInputStream(input)
            UserPreferences(
                dis.readUTF(),
                dis.readUTF(),
                dis.readUTF(),
                dis.readUTF()
            )
        } catch (exception: Exception) {
            UserPreferences("", "", "", "")
        }
    }

    override suspend fun writeTo(
        t: UserPreferences,
        output: OutputStream
    ) {
        val dos = DataOutputStream(output)
        dos.writeUTF(t.uuid)
        dos.writeUTF(t.name)
        dos.writeUTF(t.email)
        dos.writeUTF(t.photoUrl)
    }
}
