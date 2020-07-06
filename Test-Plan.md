## Тест план HDFS:
**1. Introduction (Вступление)** 
Этот документ является тест планом к Hadoop Distributed File System (HDFS), которая является неотъемленной частью Hadoop. 
В нем описано что планируется тестировать, что необходимо для успешного тестирования, какие техники тестирования планируются использоваться, риски.

**2. Возможный функционал для тестирования:** 
 
    Folder Actions:	
        - Rename Folder
        - Move Folder
        - New Folder
        - Copy Folder
        - Move to trash Folder
        - Delet forever Folder
        - Change permissions Folder

	File Actions: 
        - New File 
        - Open File
        - Edit File
        - Rename File 
        - Move File
        - Copy File
        - Download File
        - Move to trash file 
        - Delet forever File
        - Change permissions File

    Other: 		
        - New Directory
        - Upload file
        - Upload Archive File
        - Clear history
        - Empty Trash
        - Search for file name
        - Sorting by Name/Size/User/Group/Permissions/Date
 
**3. Функциональности которые будут тестироваться(автотестами):** 

    - New Folder
    - New File
    - Copy File


**4. Функциональности которые не будут тестироваться:**
   
        - Автоматизация всех кейсов для всех видов тестирования

**5. Для автотестов будут использоваться:** Hadoop API ver.2.7.1, cloudera/quickstart докер-контейнер, Java IDEA.
   
**6. Риски:**  
Из-за нестабильности контейнера возможны риски при тестах. Нестабильность контейнера cloudera/quickstart проявляется в следующем: при прогоне автотестов связанных непосредственно введение данных падает ошибка репликации, и из-за этого введение любых данных невозможно. 
Для успешного тестирования необходима настройка нодов и настройка прав доступа. 








## Тест-кейсы для автотестов:
1. New File

        - Open hdfs browser
        - Нажать кнопку New
        - Выбрать новый файл 
        - Задать имя файла
        - Нажать кнопку create 

2. New Folder

		- Open hdfs browser
        - Нажать кнопку New
        - Выбрать новый Directory 
        - Задать имя папки
        - Нажать кнопку create 

3. Copy exist file

		- Создать папку 1
		- Создать папку 2
		- Создать файл 1 в папке 1
		- Скопировать файл (ПКМ)
		- Выбрать путь до папки 2
		- Ок