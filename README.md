# WEB1
Java threading problem. Synchronization, CyclicBarrier, Semaphores.


N studenata pristupa odbrani domaceg zadatka (svaki proces odbrane se izvrsava u posebnoj niti). Odbranu drze profesor I asistent.  Svaki student brani zadatak svojim tempom (random vrednost u opsegu od 0.5 <=  X <= 1sekund).  Asistent je u stanju da vrsi uvid u rad samo jednog studenta dok je profesor sposoban da prati dve odbrane paralelno. Svaki student na odbranu dolazi u nekom vremenskom perdiodu (random vrednostu u opesgu od  0 < x <= 1sekund od pocetka termina za odbranu*). Termin za odbranu traje 5 sekundi. Odbrana koja je zapoceta mora biti prekinuta onog tenutka kada se zavrsi termin za odbranu*. 
 
Profesor ne prihvata da radi uvid samo jednog studenta nego ce sacekati dva studenta koji su spremni da brane I tek onda da radi uvid oba studenta paralelno, dok oba ne zavrse. 
 
Asistent radi uvid cim se neki student “spremi” za odbranu. 

Verovatnoca da student brani kod profesora ili asistenta neka bude po izboru (npr. 50% da ce braniti kod profesora i 50% da ce braniti kod asistenta).
 
Svaki student po zavrsetku svoje odbrane mora da osvezi sumu ocena svih studenata tako sto ce sabrati I svoju. Ove ocene ce se po zavrsetku programa podeliti sa brojem studenata I dobijeni prosek treba da se ispise u konzoli po zavrsetku termina obrade. Ovaj prosek mora biti tacan bez obzira na broj studenata. 
 
Profesor moze da radi uvid u rad od tacno dva studenta dok asistent moze da vrsi uvid u tacno jedan rad.  Ne sme da se dogodi da isti student dva puta brani domaci. Ne sme da se dogodi da student brani domaci posle zavrsetka termina odbrane cak I ako je poceo pre zavrsetka. Ne sme da se dogodi da profesor I asistent rade uvid istom studentu. 
 
*Termin odbrane -> Termin pocinje onog trenutka kada su I profesorska I asistentska nit sposobne da rade uvid. Termin se zavrsava 5 sekundi posle toga. 
 
Ulazni parameter sistema je N tj. broj studenata.  
 
Izlaz sistema treba da bude ispis sledeceg formata za sve student koji su stigli ili su prekinuti u odbrani rada: 
 
Thread: <Ime treda studenta> Arrival: <Vreme prispeca stundenta od pocetka odbrane> Prof: <Ime treda asistenta ili profesora> TTC: <Vreme koje je bilo potrebno da se pregleda domaci>:<vreme pocetka odbrane> Score: <Ocena koju je dobio od 0 do 10> 
 
Za kreiranje niti koristiti thread pool bilo kog tipa (sem singleThreadPool-a). Za profesora implementirati cyclicBarrier kad se ceka da dva studenta krenu sa odbranom kod njega. 
