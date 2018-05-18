#build jar
#javac -cp lib\java-json.jar dijkstra\Dijkstra.java
#jar cfm Dijkstra.jar manifest.txt dijkstra

$data1 = Get-Date
$graf = "", "", ""
$czas = ""

For ($i=0; $i -lt 5; $i++)
{
	For ($j=1; $j -lt 4; $j++)
	{
		$ile = $j*100
		$czas = java -jar .\Dijkstra.jar $ile
		$graf[$j-1]+= $czas
		$graf[$j-1] += "`r`n"
		
		write-host "graf " $ile "x" $ile ""
		write-host "  Czas:" $czas"ms"
		write-host ""
	}	
}

$data1.ToUniversalTime() >> output.log
"----------------------------------------- J A V A ------------------------------------------" >> output.log
"Graf 100x100" >> output.log
$graf[0]  >> output.log
"Graf 200x200" >> output.log
$graf[1] >> output.log
"Graf 300x300" >> output.log
$graf[2]  >> output.log


write-host "Press any key to continue..."
[void][System.Console]::ReadKey($true)


