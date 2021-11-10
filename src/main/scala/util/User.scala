package util

case class User(hashId: String, loadDate: String, recordId: String, clientSin: String,
                surname: String, name: String, patronymic: Option[String], birthDate: String,
                birthPlace: Option[String], primaryIdType: String, primaryIdNumber: String,
                deleteFlag: String)
