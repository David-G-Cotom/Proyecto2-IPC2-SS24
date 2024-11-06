export class ContenidoReporteComentario {

    numeroRevista: string;  //NO ES EL ID
    nombreRevista: string;
    fechaComentario: string;
    contenidoComentario: string;
    nombreComentarista: string;

    constructor(numeroRevista: string, nombreRevista: string, fechaComentario: string,
                contenidoComentario: string, nombreComentarista: string) {
        this.numeroRevista = numeroRevista;
        this.nombreRevista = nombreRevista,
        this.fechaComentario = fechaComentario;
        this.contenidoComentario = contenidoComentario;
        this.nombreComentarista = nombreComentarista;
    }

}