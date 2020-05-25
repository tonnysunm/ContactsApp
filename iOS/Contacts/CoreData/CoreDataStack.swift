//
//  CoreDataStack.swift
//  Contacts
//
//  Created by TonnyLi on 23/05/20.
//  Copyright © 2020 tonnysunm. All rights reserved.
//

import Foundation
import CoreData

class CoreDataStack: NSObject {
    
    static var shared = CoreDataStack()
    private override init() {}
    

    private lazy var persistentContainer: NSPersistentContainer = {
        return NSPersistentContainer(name: "Contacts").apply {
            $0.loadPersistentStores(completionHandler: { (storeDescription, error) in
                
                error.ifSome {
                    fatalError("Unresolved error \($0)")
                }
            })
        }
    }()
    
    
    fileprivate lazy var backgroundContext: NSManagedObjectContext = {
        return self.persistentContainer.newBackgroundContext().apply {
            $0.name = "Background ctx"
        }
    }()
    
    
    lazy var mainContext: NSManagedObjectContext = {
        return self.persistentContainer.viewContext.apply {
            $0.name = "Main ctx"
            $0.automaticallyMergesChangesFromParent = true
        }
    }()
}


extension CoreDataStack {
    
    static func performBackgroundTask(_ task: @escaping (NSManagedObjectContext) throws -> Result<[TypeOfId], AppError>,
                                      completion: @escaping DBIdsResultCompletion) {
        
        shared.persistentContainer.performBackgroundTask { context in
            
            let result: Result<[TypeOfId], AppError>
            do {
                result = try task(context)
            }catch {
                completion(.failure(.coredata(error.localizedDescription)))
                return
            }
            
            
            do {
                try context.save()
            }catch {
                return completion(.failure(.coredata(error.localizedDescription)))
            }
            
            completion(result)
        }
    }
}

extension NSManagedObjectContext {
    var nickName: String {
        return name ?? "some background ctx"
    }
}
