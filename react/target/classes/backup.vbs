Dim arg, fileName
Set arg = WScript.Arguments
fileName = Arg(0)
Set excel = CreateObject("Excel.Application")
Set lang = excel.Application.LanguageSettings

Set fso = CreateObject("Scripting.FileSystemObject")
Set stdout = fso.GetStandardStream(1)
stdout.WriteLine(excel.Application.International(3))
stdout.WriteLine(excel.Application.International(4))
stdout.WriteLine(lang.LanguageID(2))
stdout.Close

Set stdout = Nothing

Set objFileToWrite = CreateObject("Scripting.FileSystemObject").OpenTextFile(fileName,2,true)
objFileToWrite.WriteLine(excel.Application.International(3))
objFileToWrite.WriteLine(excel.Application.International(4))
objFileToWrite.WriteLine(lang.LanguageID(2))
objFileToWrite.Close
Set objFileToWrite = Nothing
Set arg = Nothing