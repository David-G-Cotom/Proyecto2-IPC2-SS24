export class ContenidoReporteRevistasTop {

    numeroRevista: string;  //NO ES EL ID
    nombreRevista: string;
    cantidadLikes: number;
    nombresSuscriptores: string[];
    fechasLikes: string[];

    constructor(numeroRevista: string, nombreRevista: string, cantidadLikes: number,
                nombresSuscriptores: string[], fechasLikes: string[]) {
        this.numeroRevista = numeroRevista;
        this.nombreRevista = nombreRevista;
        this.cantidadLikes = cantidadLikes;
        this.nombresSuscriptores = nombresSuscriptores;
        this.fechasLikes = fechasLikes;
    }

}