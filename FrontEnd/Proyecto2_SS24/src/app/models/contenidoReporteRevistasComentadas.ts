export class ContenidoReporteRevistaComentada {

    nombreEditor: string
    descripcion: string;
    nombreRevista: string;
    cantidadComentarios: number;
    nombresSuscriptores: string[];
    comentarios: string[]
    fechasComentarios: string[];

    constructor(nombreEditor: string, descripcion: string, nombreRevista: string,
                cantidadComentarios: number, nombresSuscriptores: string[],
                comentarios: string[], fechasComentarios: string[]) {
        this.nombreEditor = nombreEditor;
        this.descripcion = descripcion;
        this.nombreRevista = nombreRevista;
        this.cantidadComentarios = cantidadComentarios;
        this.nombresSuscriptores = nombresSuscriptores;
        this.comentarios = comentarios;
        this.fechasComentarios = fechasComentarios;
    }

}