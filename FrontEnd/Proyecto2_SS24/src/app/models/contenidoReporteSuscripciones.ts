export class ContenidoReporteSuscripcion {

    numeroRevista: string;  //NO ES EL ID
    nombreRevista: string;
    fechaSuscripcion: string;
    nombreSuscriptor: string;

    constructor(numeroRevista: string, nombreRevista: string, fechaComentario: string,
                nombreComentarista: string) {
        this.numeroRevista = numeroRevista;
        this.nombreRevista = nombreRevista,
        this.fechaSuscripcion = fechaComentario;
        this.nombreSuscriptor = nombreComentarista;
    }

}