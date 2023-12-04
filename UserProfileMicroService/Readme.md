## MongoDB işlemleri
Not: Mongo ile işlem yaparken admin kullanıcısı ve admin şifresi kullanılmamalıdır. 
Bu nedenle oluşturulacak her bir DB için yeni bir kullanıcı ve şifre tanımlanmalıdır.
1- Öncelikle DB oluşturun: UserProfileDB
2- Üzerinde çalışma yapabilmek için mongoDB Compass üzerinde MONGOSH'ı açın. (sol altta)
3- "use databaseadı" şeklinde komut girilir.
4- bu DB'yi yönetecek olan bir kullanıcı tanımlamalıyız.
        db.createUser(
        {
            user:"defaultUser",
            pwd:"bilge!123",
            roles: ["readWrite","dbAdmin"]
        }
        )

db.createUser({user:"defaultUser",pwd:"bilge!123",roles: ["readWrite","dbAdmin"]})