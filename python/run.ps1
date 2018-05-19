#build pyc
#python -c "import py_compile; py_compile.compile('dijkstra.py', 'dijkstra.pyc')"


$data1 = Get-Date
$graf = "", "", ""
$czas = ""

For ($i=0; $i -lt 5; $i++)
{
	For ($j=1; $j -lt 4; $j++)
	{
		$ile = $j*100
		$czas = py .\dijkstra.pyc $ile
		$graf[$j-1]+= $czas
		$graf[$j-1] += "`r`n"
		
		write-host "graf " $ile "x" $ile ""
		write-host "  Czas:" $czas"ms"
		write-host ""
	}	
}

$data1.ToUniversalTime() >> output.log
"---------------------------------------- P Y T H O N ----------------------------------------" >> output.log
"Graf 100x100" >> output.log
$graf[0]  >> output.log
"Graf 200x200" >> output.log
$graf[1] >> output.log
"Graf 300x300" >> output.log
$graf[2]  >> output.log


write-host "Press any key to continue..."
[void][System.Console]::ReadKey($true)


