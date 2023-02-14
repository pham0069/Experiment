Set excel = CreateObject("Excel.Application")
Set lang = excel.Application.LanguageSettings
Set fso = CreateObject("Scripting.FileSystemObject")
Set stdout = fso.GetStandardStream(1)
stdout.WriteLine(excel.Application.International(3))
stdout.WriteLine(excel.Application.International(4))
stdout.WriteLine(lang.LanguageID(2))
stdout.Close
Set stdout = Nothing