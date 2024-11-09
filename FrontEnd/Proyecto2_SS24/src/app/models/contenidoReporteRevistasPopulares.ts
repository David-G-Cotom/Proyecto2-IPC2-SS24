export class ContenidoReporteRevistaPopular {

    nombreEditor: string
    descripcion: string;
    nombreRevista: string;
    cantidadSuscripciones: number;
    nombresSuscriptores: string[];
    fechasSuscripciones: string[];

    constructor(nombreEditor: string, descripcion: string, nombreRevista: string,
                cantidadSuscripciones: number, nombresSuscriptores: string[],
                fechasSuscripciones: string[]) {
        this.nombreEditor = nombreEditor;
        this.descripcion = descripcion;
        this.nombreRevista = nombreRevista;
        this.cantidadSuscripciones = cantidadSuscripciones;
        this.nombresSuscriptores = nombresSuscriptores;
        this.fechasSuscripciones = fechasSuscripciones;
    }

}