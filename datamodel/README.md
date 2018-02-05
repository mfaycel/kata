# Important Hibernate Functions save, persist, saveOrUpdate, update, merge, get, load

https://github.com/awantik/MindTree-Hibernate-8/wiki/Important-Hibernate-Functions---save,-persist,-saveOrUpdate,-update,-merge,-get,-load



##Terminology

Persistent Object - Objects along with which you call save() or persist().

###save

Used to save entity in database
Issue: If we use this without transaction & we have cascading between entities, then only primary key gets save unless we flush the session.
For example, Employee have address. Employee & Address gets saved to different table. If we use save() without doing session.flush()/close(), only primary key gets saved not others.
We should not use save outside transaction boundaries.
save() method returns generated id immediately, this is possible because primary object is saved as soon as save method is invoked
If there are other objects mapped to the primary object, they gets saved at the time of committing transaction.
For the objects in persistent state, save updates the data through update query. This happens when tx.commit() is done.
save() load entity object to persistent context.
###persist

Similar to save() & persist() adds entity object to persistent context.
Persist itself takes care of cascaded objects. That means the related objects.
Persist doesn't return anything, persist object is used to get generated identifier.
###saveOrUpdate

This results into insert or update queries based on data. If the data is present in the database, update will be executed.
We can use saveOrUpdate without transaction, but issue will mapped objects.
adds entity objects to persistent cotext & track any further changes. Any further changes can be persisted using persist.
###update

Should be used only when you know that you are updating the entity information.
Update doesn't return anything.
###Merge

Used to update existing values.

This method creates a copy from the passed entity object & return it.

This means the returned object is different from passed object.

Return object can be tracked for further changes but passed object cannot.

Employee emp = (Employee) session.load(Employee.class, 1L); emp.age = 66; emp.addr.name = "bangalore"; Transaction tx = session.beginTransaction(); Employee emp1 = session.merge(emp); //emp1 is tracked for changes //emp is not tracked for changes emp1.age = 100 emp.age = 50 tx.commit() session.close()

###session.load(Student.class,1L) This is to bring persistent object as entity

This always return a proxy object.

Proxy Object - hibernate will always prepare some fake object with a id (1L) in memory without hitting db.

Delay's memory access

It won't populate other member information like name & salary.

The other information will be populated only when we try to retrieve the other properties.

If row is null, it returns ObjectNotFoundException

Employee e1 = (Employee) session.load(Employee.class, 45L); if ( e1 != null ){ System.out.println("E1 present"); }

Hibernate database query don't happen & log printed

###session.get(Student.class,1L) This is to bring persistent object as entity

It hits database immediately & returns the original object.

If row is null, it returns null

Employee e1 = (Employee) session.get(Employee.class, 35L); if ( e1 != null ){ System.out.println("E1 present"); }

Hibernate database query happens & log not printed

###Performance

Load will do little better but if object is not found, it throws exception.
###When to use Use get to check if entity is existing in db & load only when you are sure that entity is existing & entity non-existing is not an option.