<#setting number_format="#">
<ul>
<#list chordFretList as chordFret>
	<li>${chordFret.chordId}:${chordFret.rootNote}:[${chordFret.fretNumbers}]:[${chordFret.finger}]</li>
</#list>
</ul>